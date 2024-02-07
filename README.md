# This repository is all about core java concepts, a java project made with Spring boot and a selenium project.<br>
## <i>The java core covers these topics</i>
<table>
    <thead>
        <tr>
            <th>Java Concept</th>
            <th>Class Name/Classes Used</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Hashcode/Equals/Sorting Objects</td>
            <td><code>Person</code> class</td>
            <td>Demonstrates the implementation of <code>hashCode()</code>, <code>equals()</code>, and sorting objects based on a custom criterion.</td>
        </tr>
        <tr>
            <td>Collections/List/Set/Map</td>
            <td><code>ArrayList</code>, <code>HashSet</code>, <code>HashMap</code></td>
            <td>Examples of using Java collections: list, set, and map.</td>
        </tr>
        <tr>
            <td>Working with Files</td>
            <td><code>FileInputStream</code>, <code>FileOutputStream</code></td>
            <td>Illustrates reading and writing to files using traditional file I/O.</td>
        </tr>
        <tr>
            <td>Date Conversions</td>
            <td><code>DateTimeFormatter</code>, <code>ZonedDateTime</code></td>
            <td>Converts user input date strings, performs various date conversions, and manipulations.</td>
        </tr>
        <tr>
            <td>Threads/Executor Service</td>
            <td><code>ExecutorService</code>, <code>Runnable</code></td>
            <td>Demonstrates basic usage of threads and the ExecutorService for concurrent execution of tasks.</td>
        </tr>
        <tr>
            <td>Encoding/Decoding</td>
            <td><code>Base64</code></td>
            <td>Examples of encoding and decoding using Base64 encoding.</td>
        </tr>
        <tr>
            <td>Encryption/Decryption</td>
            <td><code>Cipher</code>, <code>SecretKey</code></td>
            <td>Illustrates encryption and decryption using the AES algorithm.</td>
        </tr>
        <tr>
            <td>Date Conversion Assignment 1</td>
            <td><code>DateTimeFormatter</code>, <code>ZonedDateTime</code></td>
            <td>Converts user input date string to Unix Timestamp, UTC, and back from UTC to local timestamp (IST).</td>
        </tr>
        <tr>
            <td>Date Conversion Assignment 2</td>
            <td><code>DateTimeFormatter</code>, <code>LocalDateTime</code></td>
            <td>Performs date manipulations such as adding/subtracting days and hours, and sorting objects based on timestamps.</td>
        </tr>
    </tbody>
</table>

## <i>The java project is based on Spring boot</i>
The project provides three API's:

<table>
    <tr>
        <th>Endpoint</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>/api/auth/register</td>
        <td>Endpoint for user registration</td>
    </tr>
    <tr>
        <td>/api/auth/login</td>
        <td>Endpoint for user login</td>
    </tr>
    <tr>
        <td>/api/auth/all</td>
        <td>Endpoint for accessing all resources</td>
    </tr>
    <tr>
    <td>/api/blog/get</td>
    <td>Retrieve all blog posts</td>
  </tr>
  <tr>
    <td>/api/blog/get/{id}</td>
    <td>Retrieve a specific blog post by ID</td>
  </tr>
  <tr>
    <td>/api/blog/update/{id}</td>
    <td>Update a specific blog post by ID</td>
  </tr>
  <tr>
    <td>/api/blog/delete/{id}</td>
    <td>Delete a specific blog post by ID</td>
  </tr>
  <tr>
    <td>/api/blog/save</td>
    <td>Save a new blog post</td>
  </tr>
</table>
<p>Things to be added</p>
<ul>
    <li>JWT authentication</li>
</ul>

## Sel-Java
In this project some automated test cases are written using selenium in which normal operations are being performed using the framework.

## Learning-App
A basic learning app allows user to access courses and allows author's to post courses in various categories.
<table>
  <thead>
    <tr>
      <th>Endpoint</th>
      <th>RequestType</th>
      <th>Request body</th>
      <th>API description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>api/auth/login</td>
      <td>POST</td>
      <td>email, password</td>
      <td>User login</td>
    </tr>
    <tr>
      <td>api/auth/register</td>
      <td>POST</td>
      <td>email, password, name</td>
      <td>User registration</td>
    </tr>
    <tr>
      <td>api/category/add</td>
      <td>POST</td>
      <td>category_name</td>
      <td>Add a new category</td>
    </tr>
    <tr>
      <td>api/category/all</td>
      <td>GET</td>
      <td></td>
      <td>Get all categories</td>
    </tr>
    <tr>
      <td>api/course/add</td>
      <td>POST</td>
      <td>name, category</td>
      <td>Add a new course</td>
    </tr>
    <tr>
      <td>api/course/fetch</td>
      <td>POST</td>
      <td>category</td>
      <td>Fetch courses by category</td>
    </tr>
    <tr>
      <td>api/course/fetch/{id}</td>
      <td>POST</td>
      <td>category</td>
      <td>Fetch a specific course by ID</td>
    </tr>
    <tr>
      <td>api/fav/make</td>
      <td>POST</td>
      <td>email, course_id</td>
      <td>Add a course to favorites</td>
    </tr>
    <tr>
      <td>api/fav/remove</td>
      <td>POST</td>
      <td>email, course_id</td>
      <td>Remove a course from favorites</td>
    </tr>
    <tr>
      <td>api/fav/all</td>
      <td>POST</td>
      <td>email</td>
      <td>Get all favorite courses for a user</td>
    </tr>
  </tbody>
</table>
Things to add:
<ul>
    <li>JWT authentication</li>
    <li>JWT based role filter</li>
    <li>Comments in code</li>
</ul>
