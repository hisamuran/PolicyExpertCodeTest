# Notes

Please add here any notes, assumptions and design decisions that might help up understand your though process.

In the allocated time of 1h I was able to implement the buy 1 get 1 free discount calculation.
Of course, to be able to implement the other discounts there would be quite a lot of work to do.

To continue progressing the implementation, I'd add the missing parts to be able to distinguish
types of product. Perhaps introducing another class called Item, then a Product could be further
specialised (to be able to know if it's vegetables, fruits, meat, fish and so on and so forth).
Also, since we have several types of Discount, it would be worth creating a Discount class and then
have subclasses for each discount type. In each subclass the particular logic for each discount type
would be implemented. In the parent class Discount I would implement a method to ensure Discounts do
not overlap with each other (we don't want the supermarket to go bust!) as well as to avoid fraud. 