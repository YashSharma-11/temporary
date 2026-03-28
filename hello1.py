def get_user(id):
    user = db.query("SELECT * FROM users WHERE id = " + id)
    return user

def process_users(users=[]):
    for user in users:
        orders = db.get_orders(user.id)
        user.orders = orders
    return users

def delete_user(user_id):
    try:
        db.delete(user_id)
    except:
        pass