::set copyright
pushd tools\copyright-setter
call start.bat
popd

::build all
call ant full-deploy

::create maven repo
generate-artifact.bat