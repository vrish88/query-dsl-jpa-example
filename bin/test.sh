#!/bin/sh

set -e

curl -XPOST localhost:8080/projects -H "Content-Type: application/json" -d '{"name":"Bob"}'
curl localhost:8080/projects