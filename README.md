VIDEO GAME API
----------------------------------------------------------------------------
localhost:8084 is the default way to access the api

Common Usage

  GET
    /users <br />
      Gets all users <br />
      
    /users/{userId} <br />
      Gets a user specified by the userId <br />
      
    /games <br />
      Gets all games <br />
      
    /games/{gameId} <br />
      Gets a game specified by the gameId <br />
      
    /offers <br />
      Gets all offers <br />
      
    /offers/{offerId} <br />
      Gets an offer specified by the offerId <br />
      
-------------------------------------------------------------
  POST: <br />
    /users: <br />
      Posts a user to the database <br />
      Expected request body: <br />
        { <br />
          "id": 0, <br />
          "name": "string", <br />
          "email": "string", <br />
          "address": "string", <br />
          "city": "string", <br />
          "state": "string", <br />
          "zip": "string", <br />
          "password": "string", <br />
          "games": [ <br />
            "string" <br />
          ] <br />
        } <br />
    /users/{userId}/{gameId} <br />
      Posts a game to a user object given a userId and a gameId <br />
    /games <br />
      Posts a game to the database <br />
      Expected request body: <br />
        { <br />
          "id": 0, <br />
          "title": "string", <br />
          "publisher": "string", <br />
          "pubishYear": "string", <br />
          "condition": "string", <br />
          "console": "string", <br />
          "numOwners": 0 <br />
        } <br />
    /offers <br />
      Posts an offer to the database <br />
      Expected request body: <br />
        { <br />
          "id": 0, <br />
          "fromUser": "string", <br />
          "sentGames": [ <br />
            "string" <br />
          ], <br />
          "receivedGames": [ <br />
            "string" <br />
          ], <br />
          "toUser": "string", <br />
          "timeCreated": "string", <br />
          "state": "string" <br />
        } <br />
    /offers/{offerId}/{gameId}/{isSent} <br />
      Adds a game to an offer given an offerId, gameId, and a boolean for if it was sent or received <br />
--------------------------------------------------------------------------------------------------------------
  DELETE <br />
    /users/{userId} <br />
      Deletes a user given the userId <br />
    /users/{userId}/{gameId} <br />
      Deletes a game from a user given the userId and gameId <br />
    /games/{gameId} <br />
      Deletes a game given the gameId <br />
    /offers/{offerId} <br />
      Deletes an offer given the offerId <br />
    /offers/{offerId}/{gameId}/{isSent} <br />
      Deletes a game from an offer given the offerId. gameId, and if it was sent or received <br />
    
