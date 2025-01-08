FROM ubuntu:latest
LABEL authors="seang"

ENTRYPOINT ["top", "-b"]