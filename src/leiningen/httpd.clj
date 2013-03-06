(ns leiningen.httpd
  "Start a web server in the current directory."
  (:import (org.eclipse.jetty.server Handler Server)
           (org.eclipse.jetty.server.handler DefaultHandler HandlerList ResourceHandler)))

(defn- static-file-handler []
  "Creates a new static file handler; accessing a directory serves up an
  index.html file if present, otherwise a directory listing is
  returned."
  (doto (ResourceHandler.)
        (.setDirectoriesListed true)
        (.setWelcomeFiles (into-array ["index.html"]))
        (.setResourceBase ".")))

(defn- create-server
  "Creates a Jetty server bound to the given port, with the given list
  of request handlers."
  [port handlers]
  (let [handler-list (doto (HandlerList.)
                           (.setHandlers (into-array Handler handlers)))]
    (doto (Server. port) (.setHandler handler-list))))

(defn- run-server
  "Starts the given Jetty server and waits for it to exit."
  [server]
  (.start server)
  (.join server))

(defn ^:no-project-needed httpd
  "Start a web server in the current directory.

USAGE: lein httpd [port]
Starts a web server in the current directory, listening on the given
port.  Port 8080 will be used if no port number was supplied."
  ([project] (httpd project "8080"))
  ([project port-str]
   (let [port (Integer/parseInt port-str)
         handlers [(static-file-handler) (DefaultHandler.)]
         server (create-server port handlers)]
     (run-server server))))
