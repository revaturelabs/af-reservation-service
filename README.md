# af-reservation-service
Microservice responsible for reserving space among Revature's digital and physical 
facilities. Part of the AssignForce suite

## Environment Variables
Need to add a `.env` file in this directory  
It should include ```AUTH_SERVER=http://1.2.3.4:5555```   
or ```AUTH_SERVER=http://${spring.application.authservice}``` if using a discovery system like Consul

## Routes, Requests and Responses
All requests should have an Authorization header with a valid JWT, and will be rejected 
otherwise. Any request that does not have a JWT will be returned a `401 UNAUTHORIZED`. 
Most requests return a reservation object or list of reservation objects, each of which 
will be in this form.

```json
{
  "reservationId": "Numeric ID",
  "reserver": "Email string",
  "startTime": "Unix Epoch timestamp as number",
  "endTime": "Unix Epoch timestamp as number",
  "status": "reserved | cancelled",
  "roomId": "Numeric Room Id from path"
}
```

### Create new Reservation
`POST /rooms/{roomId}/reservations`

Expected Body:
```json
{
  "startTime": "Unix Epoch timestamp as number",
  "endTime": "Unix Epoch timestamp as number"
}
```
Possible returns:
- `201` with full reservation return body
- `404 NOT FOUND` if invalid room ID is provided in the path
- `406 NOT ACCEPTABLE` if startTime is after endTime
- `409 CONFLICT` if given times conflict with times in the database

### Get All Reservations for Room
`GET /rooms/{roomId}/reservations?begin=number&end=number`

Gets all reservations for a room with `roomId`. Optional parameters `begin` and `end` can be used to specify
a range of times within which the desired time-frame is requested. Both are optional independent of each
other meaning you could specify just a `begin` option and receive all active reservations after a given 
time, or you could specify only an `end` option and receive all active reservations before a given time.
The numbers provided should be Unix epoch times.

Possible returns:
- `200` with list of reservations bodies
- `404 NOT FOUND` if invalid room ID is provided in the path
- `406 NOT ACCEPTABLE` if begin is after end

### Get Reservation By Reservation ID
`GET /rooms/{roomId}/reservations/{reservationId}`

Gets reservation with `reservationId` for a room with `roomId`.

Possible returns:
- `200` with full reservation return body
- `404 NOT FOUND` if invalid room ID or reservation ID is provided in the path

### Update Reservation By Reservation ID
`PATCH /rooms/{roomId}/reservations/{reservationId}?action=cancel`

This method either, changes the times for a reservation in a room, or cancels a reservation
in the database if the action query param is set to "cancel". If action is set to cancel,
the body is ignored, if the action is set to anything else, it is ignored.

Expected Body:
```json
{
  "startTime": "Unix Epoch timestamp as number",
  "endTime": "Unix Epoch timestamp as number"
}
```

Possible returns:
- `200` with full reservation return body
- `404 NOT FOUND` if invalid room ID or reservation ID is provided in the path
- `406 NOT ACCEPTABLE` if startTime is after endTime
- `403 FORBIDDEN` if user is attempting to change a reservation that isn't theirs
- `409 CONFLICT` if the new times have a conflict with another reservation