[![Build Status](https://travis-ci.org/levelrin/JavaClearHttp.svg?branch=master)](https://travis-ci.org/levelrin/JavaClearHttp)
[![Test Coverage](https://img.shields.io/codecov/c/github/levelrin/JavaClearHttp.svg)](https://codecov.io/github/levelrin/JavaClearHttp?branch=master)
[![Maven Central](https://img.shields.io/maven-central/v/com.levelrin/javaclearhttp.svg)](https://maven-badges.herokuapp.com/maven-central/com.levelrin/javaclearhttp)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE)

# JavaClearHttp

JavaClearHttp is yet another HTTP client in Java.
Why reinventing the wheel? You may ask.
As the name suggests, its purpose is to show the HTTP communication transparently.
The clear communication may help you on various tasks such as API investigation or documentation.
For example, you may want to write some tutorial on GitHub API.
It would be helpful to document what messages you exactly sent and what replies exactly the server gave you.
Sure, you can do it with more powerful HTTP clients such as [Apache HttpClient](https://hc.apache.org/httpcomponents-client-ga/).
However, we believe JavaClearHttp provides easier way to obtain the information about HTTP communication.

## Usages

This section shows some examples of JavaClearHttp usages.

### Send GET request

```java
RecordType record = new Http("https://api.github.com/users/octocat/orgs")
    .get()
    .send();
System.out.println(record);
```

Output:
<pre>
Host: api.github.com
Path: /users/octocat/orgs
Protocol: HTTPS
Port: 443
Method: GET

Messages:
GET /users/octocat/orgs HTTP/1.1
Host: api.github.com
User-Agent: JavaClearHttp
Accept: */*
Connection: close


Replies:
HTTP/1.1 200 OK
Date: Fri, 08 Nov 2019 15:37:41 GMT
Content-Type: application/json; charset=utf-8
Content-Length: 2
Connection: close
Server: GitHub.com
Status: 200 OK
X-RateLimit-Limit: 60
X-RateLimit-Remaining: 58
X-RateLimit-Reset: 1573231061
Cache-Control: public, max-age=60, s-maxage=60
Vary: Accept
ETag: "98f0c1b396a4e5d54f4d5fe561d54b44"
X-GitHub-Media-Type: github.v3; format=json
Access-Control-Expose-Headers: ETag, Link, Location, Retry-After, X-GitHub-OTP, X-RateLimit-Limit, X-RateLimit-Remaining, X-RateLimit-Reset, X-OAuth-Scopes, X-Accepted-OAuth-Scopes, X-Poll-Interval, X-GitHub-Media-Type
Access-Control-Allow-Origin: *
Strict-Transport-Security: max-age=31536000; includeSubdomains; preload
X-Frame-Options: deny
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Referrer-Policy: origin-when-cross-origin, strict-origin-when-cross-origin
Content-Security-Policy: default-src 'none'
Vary: Accept-Encoding
X-GitHub-Request-Id: 0503:74A2:13760E:19F8DB:5DC58BC5

[]

</pre>

### Send POST request

```java
final String content = "{\"title\": \"Found a bug\",\"body\": \"I am having a problem with this.\"}";
final RecordType record = new Http("https://api.github.com/repos/levelrin/demo/issues")
    .post()
    .header("Authorization", "Basic credentials")
    .header("Content-Type", "application/json")
    .body(content)
    .send();
System.out.println(record);
```

Output:
<pre>
Host: api.github.com
Path: /repos/levelrin/demo/issues
Protocol: HTTPS
Port: 443
Method: POST
Body: {"title": "Found a bug","body": "I am having a problem with this."}

Messages:
POST /repos/levelrin/demo/issues HTTP/1.1
Authorization: Basic credentials
Content-Type: application/json
Host: api.github.com
User-Agent: JavaClearHttp
Accept: */*
Content-Length: 67
Connection: close

{"title": "Found a bug","body": "I am having a problem with this."}

Replies:
HTTP/1.1 201 Created
Date: Fri, 08 Nov 2019 15:43:07 GMT
Content-Type: application/json; charset=utf-8
Content-Length: 1692
Connection: close
Server: GitHub.com
Status: 201 Created
X-RateLimit-Limit: 5000
X-RateLimit-Remaining: 4999
X-RateLimit-Reset: 1573231387
Cache-Control: private, max-age=60, s-maxage=60
Vary: Accept, Authorization, Cookie, X-GitHub-OTP
ETag: "425cbcdbe9e270b5af46324dff2e6bd8"
X-OAuth-Scopes: admin:enterprise, admin:gpg_key, admin:org, admin:org_hook, admin:public_key, admin:repo_hook, delete:packages, delete_repo, gist, notifications, read:packages, repo, user, workflow, write:discussion, write:packages
X-Accepted-OAuth-Scopes: 
Location: https://api.github.com/repos/levelrin/Demo/issues/8
X-GitHub-Media-Type: github.v3; format=json
Access-Control-Expose-Headers: ETag, Link, Location, Retry-After, X-GitHub-OTP, X-RateLimit-Limit, X-RateLimit-Remaining, X-RateLimit-Reset, X-OAuth-Scopes, X-Accepted-OAuth-Scopes, X-Poll-Interval, X-GitHub-Media-Type
Access-Control-Allow-Origin: *
Strict-Transport-Security: max-age=31536000; includeSubdomains; preload
X-Frame-Options: deny
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Referrer-Policy: origin-when-cross-origin, strict-origin-when-cross-origin
Content-Security-Policy: default-src 'none'
Vary: Accept-Encoding
X-GitHub-Request-Id: 0506:1D4E:1202DFD:16D53A7:5DC58D0A

{"url":"https://api.github.com/repos/levelrin/Demo/issues/8","repository_url":"https://api.github.com/repos/levelrin/Demo","labels_url":"https://api.github.com/repos/levelrin/Demo/issues/8/labels{/name}","comments_url":"https://api.github.com/repos/levelrin/Demo/issues/8/comments","events_url":"https://api.github.com/repos/levelrin/Demo/issues/8/events","html_url":"https://github.com/levelrin/Demo/issues/8","id":520084924,"node_id":"MDU6SXNzdWU1MjAwODQ5MjQ=","number":8,"title":"Found a bug","user":{"login":"levelrin","id":51286045,"node_id":"MDQ6VXNlcjUxMjg2MDQ1","avatar_url":"https://avatars0.githubusercontent.com/u/51286045?v=4","gravatar_id":"","url":"https://api.github.com/users/levelrin","html_url":"https://github.com/levelrin","followers_url":"https://api.github.com/users/levelrin/followers","following_url":"https://api.github.com/users/levelrin/following{/other_user}","gists_url":"https://api.github.com/users/levelrin/gists{/gist_id}","starred_url":"https://api.github.com/users/levelrin/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/levelrin/subscriptions","organizations_url":"https://api.github.com/users/levelrin/orgs","repos_url":"https://api.github.com/users/levelrin/repos","events_url":"https://api.github.com/users/levelrin/events{/privacy}","received_events_url":"https://api.github.com/users/levelrin/received_events","type":"User","site_admin":false},"labels":[],"state":"open","locked":false,"assignee":null,"assignees":[],"milestone":null,"comments":0,"created_at":"2019-11-08T15:43:07Z","updated_at":"2019-11-08T15:43:07Z","closed_at":null,"author_association":"OWNER","body":"I am having a problem with this.","closed_by":null}

</pre>

## How to use?

You just need to add the dependency like so:

Gradle:
```groovy
dependencies {
    implementation 'com.levelrin:javaclearhttp:0.1.0'
}
```

Maven:
```xml
<dependency>
  <groupId>com.levelrin</groupId>
  <artifactId>javaclearhttp</artifactId>
  <version>0.1.0</version>
</dependency>
```

Requirements:
1. JDK 1.8+

## How to contribute?

1. Create a [ticket](https://github.com/levelrin/JavaClearHttp/issues).
1. Run `./gradlew build` and make sure the build is clean.
1. Modify the code.
1. Send a [pull request](https://github.com/levelrin/JavaClearHttp/pulls).
