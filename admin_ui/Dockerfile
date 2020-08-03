FROM openjdk:8-jdk-alpine

ARG JAR_FILE

ENV DATABASE_URL = "jdbc:postgresql://localhost:5432/shop_db"

COPY ./target/${JAR_FILE} /bin/app.jar
COPY ./entrypoint.sh /bin/entrypoint.sh
RUN chmod +x /bin/entrypoint.sh

CMD ["/bin/entrypoint.sh"]

EXPOSE 8080
EXPOSE 5432