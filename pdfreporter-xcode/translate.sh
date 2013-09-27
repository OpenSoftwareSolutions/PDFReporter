red='\x1B[1;34m'
nc='\x1B[0m' # No Color

echo -e "${red}Translating Core...${nc}"
j2objc -classpath ../pdfreporter-core/src:../pdfreporter-extensions/src -d tmp/core -use-arc --ignore-missing-imports `find ../pdfreporter-core/src -name '*.java'` | grep error

echo
echo -e "${red}Translating Extensions...${nc}"
j2objc -classpath ../pdfreporter-core/src:../pdfreporter-extensions/src -d tmp/ext -use-arc --ignore-missing-imports `find ../pdfreporter-extensions/src -name '*.java'` | grep error

echo
echo -e "${red}Translating Portable...${nc}"
j2objc -classpath ../pdfreporter-core/src:../pdfreporter-extensions/src:../pdfreporter-portable/src:../pdfreporter-java/src -d tmp/port -use-arc --ignore-missing-imports `find ../pdfreporter-portable/src -name '*.java'` | grep error
