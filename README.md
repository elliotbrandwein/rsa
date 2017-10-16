# rsa
These set of programs will create a rsa key, as well as encrypt and decrypt a message using the keys generated (probably)

# How To use it

Run the rsa program via the line "java rsa {file name}" to generate your keys and have them written to your file of choice.

Once your key file has been created, write your message into a plain text file of your choice your current working directory. 

To encrypt the message, simply run the encrypt program via the command line "java encrypt {name of key file} {name of plain text message file}". This program will then create a new file called encrypted_message.txt and fill it with the encrypted message. If such a file already exists it will replace what was in it with the new message.

To decrypt the message, run the command "java decrypt {name of key file} {name of encrypted message file} {name of output file}", where the name of the output file is the file you want the message to be placed in. If the file already exists it will replace its contents with the message. It will also print out the decrypted message to the console. 
 
# TODO
 I'm working on a new set of programs called "encrypt_file" and "decrypt_file" that will do the same things as encrypt and decrypt but they will work on any type of file, and not just text.
 The next goal after that is to encrypt the key file.
 The last goal is to put this whole program into a gui and make it look nice. 