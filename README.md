# demo
Database 
User
- id: string (primary key)
- username: string

Post
- id: string (primary key)
- userId: string (foreign key to User)
- title: string
- body: string
- createdAt: timestamp

Review
- id: string (primary key)
- postId: string (foreign key to Post)
- userId: string (foreign key to User)
- rating: integer (between 1 and 5, inclusive)
- comment: string
- createdAt: timestamp
