package user.userprivacy;

public class DeleteUserCommand implements Command{
    User user;

    public DeleteUserCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() {
        user.deleteUser(user);
    }
}
