playforjava-warehouse
=====================

Play Framework version: 2.1.1

Warehouse sample application created by following Play for Java MEAP book (PDF version as of 2013-04-16 11:15). To switch to MySQL connection create local mysql database as follows:

<pre>
DB name : play
User    : play
Password: play
</pre>

Make sure you uncomment the default database connection configuration of your 
choice in conf/application.conf as follows:

<pre>
# H2 connection
db.default.driver=org.h2.Driver
db.default.url="jdbc:h2:mem:play"
db.default.user=sa
db.default.password=""

# MySQL connection
#db.default.driver=com.mysql.jdbc.Driver
#db.default.url="jdbc:mysql://localhost/play"
#db.default.user=play
#db.default.password=play
</pre>
