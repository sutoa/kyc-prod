This is a PoC to build a web-based application using AI. 
The application consists of a browser-based UI and a springboot-based backend.
It's main functions are the maintenance of employee infomration. below are the detailed functions:
1. we need to be able to maintain the following info for each employee - a list of legal entities and a list of locations he/she is associated with. Note the more employee information such as department, title, etc. will be needed in the future. So the data model should be extendable. 
2. all users can search for employee information by email ID
3. all users can submit changes to employee information
4. only a group of managers can approve the changes before they become offical

When designing solution, keep in mind that the backend should be implemented in springboot. However all the other choices for the solution should be CAREFULLY evaluated and presented to me with the proper reasons, pros and cons. In particular, I am keen of the following
1. a sleek and professional looking front-end is essential. Pls research popular websites with similar functions and model our solutions after those. Do mention what website you pick. Also, I'd like to see screenshots of the UI in your recommendation.
2. backend database should be oracle for production. however for the PoC, in-mem database is OK to use for demo purpose if it's easier.

The acceptance criteria of the PoC should be a functional UI app that can perform of all the functions above and REST api that allows us to search for employee info. 