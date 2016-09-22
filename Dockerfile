FROM maven:3.3-jdk-8
WORKDIR /data
EXPOSE 7272
CMD ["mvn"]