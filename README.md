API
----

### Building from local
```
# start postgres
brew services start postgresql

# setup database and user
createuser api
createdb -Oapi -Eutf8 api_dev

# here is what i ran one time to seed my database (you can use liquibase migrations)
#
#
#

# run migrations
#
```