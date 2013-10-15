red='\x1B[1;34m'
nc='\x1B[0m' # No Color

echo -e "${red}Translating Core...${nc}"
rm -rf PDFReporter-core/gen
j2objc/j2objc -classpath ../pdfreporter-core/src:../pdfreporter-extensions/src -d PDFReporter-core/gen -use-arc --ignore-missing-imports `find ../pdfreporter-core/src -name '*.java'` | grep error

echo
echo -e "${red}Translating Extensions...${nc}"
rm -rf PDFReporter-extensions/gen
j2objc/j2objc -classpath ../pdfreporter-core/src:../pdfreporter-extensions/src -d PDFReporter-extensions/gen -use-arc --ignore-missing-imports `find ../pdfreporter-extensions/src -name '*.java'` | grep error

echo
echo -e "${red}Translating Portable...${nc}"
rm -rf PDFReporter-ios/gen
j2objc/j2objc -classpath ../pdfreporter-core/src:../pdfreporter-extensions/src:../pdfreporter-portable/src:../pdfreporter-java/src -d PDFReporter-ios/gen -use-arc --ignore-missing-imports `find ../pdfreporter-portable/src -name '*.java'` | grep error

echo
echo -e "${red}Translating iOS...${nc}"
j2objc/j2objc -classpath ../pdfreporter-core/src:../pdfreporter-extensions/src:../pdfreporter-portable/src:../pdfreporter-java/src -d PDFReporter-ios/gen -use-arc --ignore-missing-imports `find ../pdfreporter-ios/src -name '*.java'` | grep error