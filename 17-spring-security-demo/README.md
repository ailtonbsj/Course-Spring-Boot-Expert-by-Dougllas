# Testing with HTTPie

```bash
# OK
http GET :8080/public

# Unauthorized
http GET :8080/
http GET :8080/groups
http GET :8080/users
http GET :8080/private
http GET :8080/admin

# OK
http -a admin:123 GET :8080/public
http -a admin:123 GET :8080/
http -a admin:123 POST :8080/groups name=FTP
http -a admin:123 GET :8080/groups
http -a admin:123 GET :8080/users
http -a admin:123 GET :8080/admin

echo '{"user": {"login": "ian", "pass": "123", "name": "Ian"}, "permissions": ["TECH", "USER"]}' | \
http -a admin:123 POST :8080/users

# Forbidden
http -a admin:123 GET :8080/private

# OK
http -a ian:123 GET :8080/public
http -a ian:123 GET :8080/
http -a ian:123 GET :8080/private

# Forbidden
http -a ian:123 GET :8080/groups
http -a ian:123 GET :8080/users
http -a ian:123 GET :8080/admin
http -a ian:123 POST :8080/groups name=FTP
```
