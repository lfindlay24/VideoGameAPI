VIDEO GAME API
----------------------------------------------------------------------------
localhost:8084 is the default way to access the api

Common Usage

  GET
  
    /users 
      Gets all users 
      
    /users/{userId} 
      Gets a user specified by the userId 
      
    /games 
      Gets all games 
      
    /games/{gameId} 
      Gets a game specified by the gameId 
      
    /offers 
      Gets all offers 
      
    /offers/{offerId} 
      Gets an offer specified by the offerId 
      
-------------------------------------------------------------
  POST
  
    /users: 
      Posts a user to the database 
      Expected request body: 
        { 
          "id": 0, 
          "name": "string", 
          "email": "string", 
          "address": "string", 
          "city": "string", 
          "state": "string", 
          "zip": "string", 
          "password": "string", 
          "games": [ 
            "string" 
          ] 
        } 
        
    /users/{userId}/{gameId} 
      Posts a game to a user object given a userId and a gameId 
      
    /games 
      Posts a game to the database 
      Expected request body: 
        { 
          "id": 0, 
          "title": "string", 
          "publisher": "string", 
          "pubishYear": "string", 
          "condition": "string", 
          "console": "string", 
          "numOwners": 0 
        } 
        
    /offers 
      Posts an offer to the database 
      Expected request body: 
        { 
          "id": 0, 
          "fromUser": "string", 
          "sentGames": [ 
            "string" 
          ], 
          "receivedGames": [ 
            "string" 
          ], 
          "toUser": "string", 
          "timeCreated": "string", 
          "state": "string" 
        } 
        
    /offers/{offerId}/{gameId}/{isSent} 
      Adds a game to an offer given an offerId, gameId, and a boolean for if it was sent or received 
      
--------------------------------------------------------------------------------------------------------------
  DELETE 
    /users/{userId} 
      Deletes a user given the userId 
    /users/{userId}/{gameId} 
      Deletes a game from a user given the userId and gameId 
    /games/{gameId} 
      Deletes a game given the gameId 
    /offers/{offerId} 
      Deletes an offer given the offerId 
    /offers/{offerId}/{gameId}/{isSent} 
      Deletes a game from an offer given the offerId. gameId, and if it was sent or received 
    
