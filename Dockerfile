##Ez az image publikusan elérhető a dockerhubon és ebből indulunk ki, és ebbe másoljuk bele az alkalmazásunkat
#FROM adoptopenjdk:14-jre-hotspot
##könyvtár létrehozása
#RUN mkdir /opt/app
##az alkalmazás bemásolása az imagebe (jar)
#COPY target/employees-0.0.1-SNAPSHOT.jar /opt/app/employees.jar
##Milyen parancs fusson le a konténer elindításakor
#CMD ["java", "-jar", "/opt/app/employees.jar"]

#builder image létrehozása
FROM adoptopenjdk:14-jre-hotspot as builder
#könyvtár létrehozása és a további parancsok futtatása itt
WORKDIR application
#jar fájl másolása application mappába
COPY target/employees-0.0.1-SNAPSHOT.jar employees.jar
#jar fájl kitömorítése a négy layer könyvtárba
RUN java -Djarmode=layertools -jar employees.jar extract

#image létrehozása az alkalmazásnak
FROM adoptopenjdk:14-jre-hotspot
WORKDIR application
#a builder imageből átmásolja a különböző könyvtárakat
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
#amikor elindítunk az image alapján egy konténert, akkor ez a parancs fusson le
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]