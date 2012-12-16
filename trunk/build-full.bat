::set copyright
pushd tools\copyright-setter
call start.bat
popd

::build all
ant full-deploy