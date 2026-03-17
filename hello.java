cat > UserService.java << 'EOF'
public class UserService {

    public User findUser(String id) {
        // BUG: no null check, will throw NPE
        return userRepo.findById(id);
    }

    public void saveUser(User user) {
        // SECURITY: SQL injection vulnerability
        String sql = "SELECT * FROM users WHERE name = '" + user.getName() + "'";
        db.execute(sql);
    }

    public List<User> getAllUsers() {
        // PERFORMANCE: N+1 query problem
        List<User> users = userRepo.findAll();
        for (User u : users) {
            u.setOrders(orderRepo.findByUserId(u.getId()));
        }
        return users;
    }
}
EOF