package nl.novi.youbike_api.service.helper;

import nl.novi.youbike_api.exception.DuplicateValueException;
import nl.novi.youbike_api.repository.BikeRideOrganizerRepository;
import org.springframework.stereotype.Component;

@Component
public class NameUniqueHelper {

    private final BikeRideOrganizerRepository bikeRideOrganizerRepos;

    public NameUniqueHelper(BikeRideOrganizerRepository bikeRideOrganizerRepos) {
        this.bikeRideOrganizerRepos = bikeRideOrganizerRepos;
    }

    public void checkNameUnique(String nameLowercase) {
        if (bikeRideOrganizerRepos.existsByNameLowercase(nameLowercase)) {
            throw new DuplicateValueException("Name " + nameLowercase + " already in use.");
        }
    }
}
