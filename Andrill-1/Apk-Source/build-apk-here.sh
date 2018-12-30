myarg=$1
echo $myarg > ./app/src/main/res/raw/server.txt
./gradlew assembleDebug
rm -rf ./adnrill-1.apk
cp ./app/build/outputs/apk/debug/app-debug.apk ./adnrill-1.apk