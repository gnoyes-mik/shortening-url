# Shortening URL Service


## Prerequisite

- Java  8+
- git



## Getting Start

1. Clone project

```tex
$ git clone https://github.com/jeff-seyong/shortening-url.git
```

2. Excute jar file

> The 8888 port must be no used state.

```tex
$ java -jar shortening-url/out/shortening_url_demo-0.0.1-SNAPSHOT.jar
```

Successfully started shortening URL service!



## How to use

### Create shortening URL

You can create shortening URL in browser

1. Open the Chrome
2. Go to "http://localhost:8888"
3. Enter the original URL you want to shorten.
4. Click the Create button.

You can see a Shortening URL below input form.

Then, Go to the Shortening URL. It redirect to original URL.



### Get the number of requests for the URL

You can get the number of requests through the original URL or shortening URL

Example.

```tex
// Using original URL
$ curl localhost:8888/url/count?originalUrl=http://www.musinsa.com

// Using shortened URL
$ curl localhost:8888/url/count?shortenedUrl=http://localhost:8888/B

// Return json
{
    originalUrl: "http://www.musinsa.com",
    shortenedUrl: "http://localhost:8888/B",
    count: 624
}
```



