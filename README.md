
# Java Humanizer

Java Humanizer is an alternative to using CAPTCHA on your site. It's heavily inspired by the fantastic [Humanizer](https://github.com/kiskolabs/humanizer) project for Rails applications.

Java Humanizer works by providing a properties file containing simple questions and their answers. The questions are easily answered by humans, but provide enough of a challenge for scripts to stop unwanted form submissions.

## Installation

Download the jar file and add to your project's classpath.

## Usage

Get an instance of the Humanizer class:

<pre><code>
Humanizer humanizer = new Humanizer();

</code></pre>
  
Ask it for a random question:

<pre><code>
Question question = humanizer.getQuestion();

</code></pre>
  
Display the question in your JSP:

<pre><code>
&lt;input type="hidden" name="humanizerQuestionId" value="${humanizerQuestion.id}" /&gt;
&lt;label for="humanizerAnswer"&gt;${humanizerQuestion.question}&lt;/label&gt;
&lt;input type="text" id="humanizerAnswer" name="humanizerAnswer" size="64" /&gt;

</code></pre>
		
In your action, check the answer:

<pre><code>
long id = // get question id from paaramters
String answer = // get answer from parameters

boolean isHuman = Humanizer.checkAnswer(id, answer);

</code></pre>

## Configuration

The jar file comes with a properties file containing a set of questions and answers embedded within the jar, but you can easily provide your own set.

The format for this file is:

<pre><code>  
question.&lt;n&gt;=Question goes here
answer.&lt;n&gt;=Answer1; Answer2

</code></pre>
  
When the file is parsed, the value of <n> is used as the question's ID so we can find the correct set of answers. Multiple answers are supported for each question, which allows for variations of the answer. For example:

<pre><code>  
question.1=Two plus two?
answer.1=4; four

</code></pre>

This allows the question to be answered with either the number 4 or the word "four".

To use a custom set of questions and answers, just provide Humanizer with a Properties object that has your custom set of questions and answers loaded.

<pre><code>
String file = "..."; // location of custom questions and answers

Properties props = new Properties();
props.load(file);

Humanizer humanizer = new Humanizer(props);

</code></pre>

## Acknowledgments

The questions included in this project are taken directly from [Humanizer](https://github.com/kiskolabs/humanizer) by [Kisko Labs](http://kiskolabs.com/)

## License

Java Humanizer is licensed under the [MIT License](http://www.opensource.org/licenses/mit-license.php), for more details see the LICENSE file.




