# IGMProject
This is a test to see my programming skills
Project Description: The project includes REST endpoints for generating HTML content and accessing data from a third-party API. It also features a custom annotation and aspect for handling retry mechanisms.

You should be able to run it following these steps.
1.Clone the repository:
git clone https://github.com/LuisAlejandroOspinaGarzon2/IGMProjecct.git

2.Change directory to your project folder:
cd IGMProjecct

3.Build the Docker image:
docker build -t spring-boot-app .

4.Run the Docker container:
docker run -p 8080:8080 spring-boot-app

5.Access the application in a web browser at http://localhost:8080/api/generate-html or through a tool like curl or Postman.
