Hey Guys. this is the real repo.
for cloning it just type the following command into the command line and it should copy into the current location:

$> git clone https://github.com/MistyPanda/US.git

be careful with spelling and capitals because git is stupid like that. good luck

after forking to set up the upstream to get changes from the master repository first you have to set up a remote to the group repository.
to do this use the command:
$> git remote add upstream https://github.com/MistyPanda/US.git
this will add the remote 'upstream' to your local repository.
then to get changes from the group repo use the command:
$> git fetch upstream
$> git merge upstream/Master
good luck
