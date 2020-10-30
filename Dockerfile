FROM openjdk:8u232-jre

RUN apt-get update && \
    apt-get install -y tzdata && \
    cp /usr/share/zoneinfo/Asia/Ho_Chi_Minh /etc/localtime && \
    echo "Asia/Ho_Chi_Minh" > /etc/timezone && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /apps
COPY ./target/app.jar app.jar
ADD entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]