# rsa
These are a set of programs will create rsa keys, as well as encrypt and decrypt a file using the keys generated (hopefully)

# How To use it

Run the rsa program via the line "java rsa {file name}" to generate your keys and have them written to your file of choice.

To encrypt your file, simply run the encrypt program via the command line "java EncryptFile {name of key file} {name of  file}". This program will then create a new file called "encrypted_{name of file}" that will encrypted. If such a file already exists it will replace what was in it with the new message.

To decrypt the message, run the command "java DecryptFile {name of key file} {name of encrypted message file} {name of output file}", where the name of the output file is the file you want the message to be placed in. If the file already exists it will replace its contents with the message.

# Warning
  This program will only encrypt files of size 245 bytes or less because as far as I can tell, that is the security standard. 

# TODO
 The next goal is to encrypt the key file.
 The goal after that is to put this whole program into a GUI and make it look nice. 