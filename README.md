# lein-httpd

A Leiningen plugin to start a web server in the current directory.  Use it for HTML/CSS/JS prototyping, for transferring files to another machine, etc.

## Usage

Add `[lein-httpd "1.0.0"]` into your global Leiningen config (`~/.lein/profiles.clj`) like so:

```clojure
{:user {:plugins [[lein-httpd "1.0.0"]]}}
```

Start a web server in the current directory on the default port (8080):

    $ lein httpd

Select a different port by supplying the port number on the command line:

    $ lein httpd 8027
