
# Submit GPX

```shell script
$ curl -H 'Accept: application/xml' -F 'file=@sample/sample.gpx' -F 'userId=1' localhost:8080/gps/new
```

# Get GPX by ID

```shell script
$ curl -H 'Accept: application/xml' localhost:8080/gps/1
```

# Get GPX by User

```shell script
$ curl -H 'Accept: application/xml' "localhost:8080/user/1/gpxes?count=1&last=0"
```

# Get User's Trackpoints

```shell script
$ curl -H 'Accept: application/xml' "localhost:8080/user/1/trackpoints?count=1&last=2017-10-22T12:30:44Z"
```