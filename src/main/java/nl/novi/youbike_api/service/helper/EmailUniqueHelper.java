package nl.novi.youbike_api.service.helper;

import nl.novi.youbike_api.exception.DuplicateValueException;
import nl.novi.youbike_api.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class EmailUniqueHelper {

    private final UserRepository userRepos;

    public EmailUniqueHelper(UserRepository userRepos) {
        this.userRepos = userRepos;
    }

    public void checkEmailUnique(String emailLowercase) {
        if (userRepos.existsByEmailLowercase(emailLowercase)) {
            throw new DuplicateValueException("Email " + emailLowercase + " already in use.");
        }
    }
}
