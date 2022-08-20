# News-App-Capstone-Project
<b>Note: This Project is dockerized, in order to run in Spring tool suite, we should change the application properties as per the requirement.</b>

<b>Steps for running the service in Docker.(Docker desktop must be installed</b>)

1. Open the <b>terminal/gitbash/command prompt</b> and move into the current directory "........Newsapp-project/Backend/newsapp-project"

2. Run the command "<b>mvn install -DskipTests</b>" and wait for some time untill it shows build success. This will download jar files.

3. Now run the command "<b>docker-compose up</b>" and wait for sometime untill the services are up and running.

4. Now open "<b>http://localhost:8761</b>" which shows all the services registered with Eureka service with status.

5. Once the services are up and running, now run Frontend part.


<b>Steps for running frontend:</b>

1. Open the <b>terminal/gitbash/command prompt</b> and move into the current directory "......./frontend".

2. Install npm dependency using command "<b>npm install</b>"

3. Once the npm is installed, run the frontend using command "<b>ng serve --open</b>"

4. This will open "<b>http://localhost:4200/login"</b> with login page to register and login.

5. Now anyone can open news articles by simply registering and login

6. Now use the application for reading news, searching news using keyword, and adding/deleting news to/from favourite and viewing favourites.
