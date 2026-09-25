package nl.novi.youbike_api.service.helper;

import nl.novi.youbike_api.model.*;
import nl.novi.youbike_api.repository.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import org.springframework.security.access.AccessDeniedException;
import java.util.List;
import java.util.Locale;

@Component
public class AuthorizationHelper {

    private final UserRepository userRepos;
    private final BikeRideRepository bikeRideRepos;
    private final BikeRepository bikeRepos;
    private final BikeCommentRepository bikeCommentRepos;

    public AuthorizationHelper(
            UserRepository userRepos,
            BikeRideRepository bikeRideRepos,
            BikeRepository bikeRepos,
            BikeCommentRepository bikeCommentRepos) {
        this.userRepos = userRepos;
        this.bikeRideRepos = bikeRideRepos;
        this.bikeRepos = bikeRepos;
        this.bikeCommentRepos = bikeCommentRepos;
    }

    public User getUser(UserDetails userDetails) {
        if (userDetails == null) {throw new AccessDeniedException("You are not authorized for this request.");}
        return userRepos.findByEmailLowercase(userDetails.getUsername().toLowerCase(Locale.ROOT)).orElseThrow(() -> new AccessDeniedException("You are not authorized for this request."));
    }

    public BikeCompany getBikeCompany(User user) {
        if (user.getBikeCompany() == null) {throw new AccessDeniedException("You are not authorized for this request because you are not a bike company.");}
        return user.getBikeCompany();
    }

    public Cyclist getCyclist(User user) {
        if (user.getCyclist() == null) {throw new AccessDeniedException("You are not authorized for this request because you are not a cyclist.");}
        return user.getCyclist();
    }

    public BikeRideOrganizer getBikeRideOrganizer(UserDetails userDetails) {
        User user = getUser(userDetails);
        if (user.getBikeCompany() != null) {return getBikeCompany(user);}
        return getCyclist(user);
    }

    public void checkUserOwnsBikeRide(long bikeRideId, UserDetails userDetails) {
        BikeRideOrganizer bikeRideOrganizer = getBikeRideOrganizer(userDetails);
        List<BikeRide> bikeRides = bikeRideRepos.findAllByOrganizer_Id(bikeRideOrganizer.getId());
        for (BikeRide bikeRide : bikeRides) {
            if (bikeRide.getId().equals(bikeRideId)) {return;}
        }
        throw new AccessDeniedException("You are not authorized for this request because you are not the organizer of this bike ride.");
    }

    public void checkUserOwnsBike(int bikeId, UserDetails userDetails) {
        User user = getUser(userDetails);
        Cyclist cyclist = getCyclist(user);
        List<Bike> bikes = bikeRepos.findAllByOwner(cyclist);
        for (Bike bike : bikes) {
            if (bike.getId().equals(bikeId)) {return;}
        }
        throw new AccessDeniedException("You are not authorized for this request because you are not the owner of the bike.");
    }

    public void checkUserOwnsBikeComment(long bikeCommentId, UserDetails userDetails) {
        User user = getUser(userDetails);
        Cyclist cyclist = getCyclist(user);
        List<BikeComment> bikeComments = bikeCommentRepos.findByAuthor_Id(cyclist.getId());
        for (BikeComment bikeComment : bikeComments) {
            if (bikeComment.getId().equals(bikeCommentId)) {return;}
        }
        throw new AccessDeniedException("You are not authorized for this request because you are not the author of the bike comment.");
    }
}
