Important Notes
\

This is the work of Daniel J. Fritsch. My pitt ID is DJF122.
My email is DJF122@pitt.edu

**All classes reference a package ass1 so that is the name the folder must have**

!!! Right now I do not have the seed in the shuffle method because that is waht
the update says. if the seed is in the method is has to be placed in one spot in the WarGame which is
written in comments in that class. Right now the program is fully random. 

!!! With the new ass1 test in the Update my code naturally doesnt match the text of the original ass1_test file 
exactly beacuse of the capacity + 1 change in Squeue. Im pretty sure this is expected but I just wanted to make note of it.
If you want the Ass1Test (not the updated version) to work it is necessary to remove the capacity + 1 from the Squeue method
and edit the deck initialization in WarGame

!!! I am assuming that with the update to the program you no longer want the orignal
ass1test to be tested but I attached that to this zip file anyway just in case. However,
as stated above my program will not match the desired output becuase of changes ennumerated in the
update that was posted. 


This project was created in Eclipse IDE for me - not sure if thats relevant but
I thought I should probably mention it. 

For the ass1_part1 test my write values start at 0 so when the ass1_part1_out.txt
file says 1 my ass1Test.java will have a write value of 1 but the queue changes the same way. 
(I know that ass1_past1_out is no longer relevant with the new tests sent in the Update but the write
increment works the same way even in the new tests we got in the Update file)



!!! I put in the new shuffle method into my Squeue class below the one I had already. If you 
want this one to be implemented the seed has to be passed as a paramter in the WarGame class where 
there is a comment showing wehre it should be. 
