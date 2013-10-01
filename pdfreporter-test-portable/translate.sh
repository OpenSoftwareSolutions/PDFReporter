red='\x1B[1;34m'
nc='\x1B[0m' # No Color

echo -e "${red}Translating test-portable...${nc}"
../pdfreporter-xcode/j2objc/j2objc -classpath ../pdfreporter-test-portable/src:../pdfreporter-test/src -d tmp -use-arc --ignore-missing-imports `find ../pdfreporter-test-portable/src -name '*.java'` | grep error

echo
echo -e "${red}Translating test...${nc}"
../pdfreporter-xcode/j2objc/j2objc  -classpath ../pdfreporter-java/src:../pdfreporter-test-portable/src:../pdfreporter-test/src:../pdfreporter-core/src -d tmp -use-arc --ignore-missing-imports `find ../pdfreporter-test/src -name '*.java'` | grep error