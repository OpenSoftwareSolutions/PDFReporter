1) Include all five Android projects and libraries in Eclipse (File -> Import -> General -> Existing Projects into Workspace)
pdfreporter-androidapp, pdfreporter-android, pdfreporter-core, pdfreporter-extensions, pdfreporter-portable
OPTIONAL - pdfrerpoter-test, pdfreporter-test-portable, pdfreporter-testdata

Dependencies:
(these are pre-setup for Eclipse, but you may have to check projects dependencies in case of build errors)

pdfreporter-androidapp (main application)
-> pdfreporter-android

pdfreporter-android
-> pdfreporter-core
-> pdfreporter-portable

pdfreporter-core
-> pdfreporter-extensions

pdfreporter-portable
-> pdfreporter-core

2) Clean all projects (Project -> Clean)
3) Run the pdfreport-androidapp as an Android application (Run As -> Android application)