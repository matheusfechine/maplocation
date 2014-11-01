maplocation
===========

Para configurar projeto para importação no eclipse basta executar o comando:

./gradlew cleanEcl ecl

Para criar a Base de Dados automaticamente mudar em applicationContext-persistence.xml a de update para create em 
prop key="hibernate.hbm2ddl.auto" update prop

O mesmo é necessário para configurar o banco de Teste no source /src/test/resource no arquivo applicationContext-persistence.xml

Banco de Dados: MySQL
