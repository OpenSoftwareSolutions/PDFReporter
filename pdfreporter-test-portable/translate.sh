red='\x1B[1;34m'
nc='\x1B[0m' # No Color

rm -rf xcode/PDFReporter-test/gen
echo -e "${red}Translating test-portable...${nc}"
../pdfreporter-xcode/j2objc/j2objc -classpath ../pdfreporter-test-portable/src:../pdfreporter-test/src -d xcode/PDFReporter-test/gen -use-arc `find ../pdfreporter-test-portable/src -name '*.java'` | grep error

echo
echo -e "${red}Translating test...${nc}"
../pdfreporter-xcode/j2objc/j2objc  -classpath ../pdfreporter-java/src:../pdfreporter-test-portable/src:../pdfreporter-test/src:../pdfreporter-core/src:../pdfreporter-java-shared/src:../pdfreporter-java-desktop/src -d xcode/PDFReporter-test/gen -use-arc `find ../pdfreporter-test/src -name '*.java'` | grep error
