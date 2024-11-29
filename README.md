# My Personal Project
## World Explorer

**What will the application do?**


The application will serve as a *guide* for discovering fun and interesting places to visit around the world. Users can browse through various categories of attractions such as parks, cafes, museums, and outdoor activities. The app will also enable users to plan their trips by creating personalized  lists, where they can add or remove destinations based on their preferences.

**Who will use it?**

Anyone looking for a trip involving an interesting or fun location to visit by themselves or with friends.

**Why is this project of interest to you?**

Google Maps has always been my go-to app for discovering new places. However, it’s not ideal for keeping track of all the locations I want to visit. This project aims to create a more personalized experience, allowing users to save, organize, and revisit their favorite spots easily.

## User Stories

- As a user, I want to be able to browse for locations by category.
- As a user, I want to be able to add a location to my favorites.
- As a user, I want to be able to view all the locations I’ve added to my favorites.
- As a user, I want to be able to create a multiple lists.
- As a user, I want to be able to view multiple lists.
- As a user, I want to be able to remove a location from my favorites.
- As a user, I want to be able to rate a location that I've been to. 

END OF PHASE 1

**PHASE 2:**
- As a user, I want to be able to save my favorite locations list as an option in the application menu.
- As a user, I want to be able to reload my save favorite locations list as an option in the application menu.

**PHASE 3: Instruction for End User**

How to add a location to a favorite list?
- First create a new list, then one can browse locations by clicking on the browse button at the top. User will choose a country then will be presented with many locations in that country. To add a location to a list, simply right click on that location and choose the list one want to be added to.

How to filter locations by category?
- User can browse different locations filtered by country by clicking the set country after the browse button.

How to remove a location from a list?
- User can remove a location from a list by viewing locations in that list and right click on the location to remove it. Simply choose the right list from the drop down option menu and you are done.

How to find the images that was used in this project?
- One can find all the images that was used in the *images* folder.

How to save the state of the application to file?
- Simply click the save button on the top to save all your lists.

How to load the state of the application from file?
- Simply click the load button on the top to load all your data.

**PHASE 4: Task 2**

Fri Nov 29 02:06:23 PST 2024

Added location to favorites: New York City

Fri Nov 29 02:06:28 PST 2024

Added location to favorites: Chicago

Fri Nov 29 02:06:31 PST 2024

Added location to favorites: Los Angeles

Fri Nov 29 02:06:37 PST 2024

Added location to favorites: Phuket

Fri Nov 29 02:06:40 PST 2024

Added location to favorites: Bangkok

Fri Nov 29 02:06:46 PST 2024

Added location to favorites: Lyon

Fri Nov 29 02:06:54 PST 2024

Removed location from favorites: Los Angeles

Fri Nov 29 02:07:02 PST 2024

Removed location from favorites: Bangkok

**PHASE 4: Task 3**

If I had more time, I would refactor the design to use interfaces for key classes like Location and Favorites. This would decouple the implementations from the rest of the system, allowing for more flexibility in the future. For example, an interface for Location could support different types of locations, while an interface for Favorites could allow for different ways to store favorites (e.g., ordered or filtered). This would make the system more modular, easier to extend, and easier to maintain in the long run.

Another potential refactoring would be to better separate the responsibilities of data management and UI interaction. Currently, the ExplorerGUI class handles both UI logic and some application logic, such as filtering locations by country. To improve this, I would move the location filtering and other core logic into a dedicated service or controller class. This would allow the ExplorerGUI to focus solely on presentation, while the application logic could be more easily tested, modified, and maintained.