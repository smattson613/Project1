FROM gradle

COPY . /workspace

WORKDIR /workspace

RUN gradle task customFatJar

EXPOSE 8080

WORKDIR /workspace/build/libs

ENTRYPOINT [ "java", "-jar", "Complaint-App-1.0-SNAPSHOT.jar" ]