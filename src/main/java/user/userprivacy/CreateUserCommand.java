package user.userprivacy;

public class CreateUserCommand implements Command {
    User user;

    public CreateUserCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() {
        user.createUser(user);
    }
}
