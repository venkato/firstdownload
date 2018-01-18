
set-executionpolicy remotesigned -s cu

Set-ExecutionPolicy RemoteSigned -scope CurrentUser


iex (new-object net.webclient).downloadstring('https://get.scoop.sh')
