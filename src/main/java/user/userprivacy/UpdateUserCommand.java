package user.userprivacy;

public class UpdateUserCommand implements Command{
    private User user;

    public UpdateUserCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() {
        user.updateUser(user);
    }
}
