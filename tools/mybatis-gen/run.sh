#!/bin/sh

# clean *.xml mapper
rm -f ../../src/main/java/com/xxx/webapp/asystem/mybatis/mapper/*.xml

file_list=`ls configfile`

run()
{
    echo $*
    $*
}

for file in $file_list
do
    run java -jar mybatis-generator-core-1.3.5.jar -configfile configfile/$file -overwrite
done
