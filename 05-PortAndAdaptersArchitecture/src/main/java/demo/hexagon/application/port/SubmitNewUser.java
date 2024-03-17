package demo.hexagon.application.port;

import demo.hexagon.application.port.commands.SubmitNewUserCommand;
import demo.hexagon.domain.model.User;

public interface SubmitNewUser {
    User submit(SubmitNewUserCommand submitNewUserCommand);
}
