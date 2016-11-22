 #!/bin/bash
 curl -X POST -H "Content-Type: application/json" --data "@./addUser.json" http://192.168.99.100:1111/pictolearn-dispatcher/proxyServlet/add -v