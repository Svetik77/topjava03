call mvn -B -s settings.xml -DskipTests=true clean install
call java -Dspring.profiles.active="datajpa,heroku" -DDATABASE_URL="postgres://postgres:root@localhost:5432/topjava03" -jar target/dependency/webapp-runner.jar target/*.war
