cat > UserService.java << 'EOF'
public class UserService {

    public User findUser(String id) {
        return userRepo.findById(id);
    }

    public void saveUser(User user) {
        String sql = "SELECT * FROM users WHERE name = '" + user.getName() + "'";
        db.execute(sql);
    }

    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        for (User u : users) {
            u.setOrders(orderRepo.findByUserId(u.getId()));
        }
        return users;
    }
}
EOF