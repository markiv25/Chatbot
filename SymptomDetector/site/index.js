document.addEventListener("DOMContentLoaded", () => {
  const inputField = document.getElementById("input");
  inputField.addEventListener("keydown", (e) => {
    if (e.code === "Enter") {
      let input = inputField.value;
      inputField.value = "";
      output(input);
    }
  });
});
 
function output(input) {
  let product;

  // Regex remove non word/space chars
  // Trim trailing whitespce
  // Remove digits - not sure if this is best
  // But solves problem of entering something like 'hi1'

  let text = input.toLowerCase();
  text = text
    .replace(/ a /g, " ")   // 'tell me a story' -> 'tell me story'
    .replace(/i feel /g, "")
    .replace(/whats/g, "what is")
    .replace(/please /g, "")
    .replace(/ please/g, "")
    .replace(/r u/g, "are you");

console.log(text);
  if (compare(prompts, replies, text)) { 
    // Search for exact match in `prompts`
    product = compare(prompts, replies, text);
  } else if (text.match(/thank/gi)) {
    product = "You're welcome!"
  }
  else if (text.match(/,/gi)) {
   // product = "Samruddhi"
  // const userAction = async () => {
  // const response = await fetch('http://localhost:8080/hello');
  // console.log(response);
  // const myJson = await response.json(); //extract JSON from the http response
  // console.log(myJson);
  // product = myJson
  // }
  // fetch('http://localhost:8080/hello')
  //   .then(response => {
  //       // handle the response
  //       console.log(response.json());
  //   })
  //   .catch(error => {
  //       // handle the error
  //       console.log(error);
  //   });
  //   product = response.json();
const data = "";
  async function getSym(input){
//	fetch('http://localhost:8080/hello'+ input {
  //method: 'POST', // or 'PUT'
  //body: JSON.stringify(data))
	const url='http://localhost:8080/hello/'+input;
    let response= await fetch(url);
    let data = await response.json();
    text=JSON.stringify(data);
	text=JSON.parse(text);
	console.log(text.content);
	addSymptom(text.content);
	return text
  }
 
  
  getSym(input).then(data=> 
 console.log(JSON.stringify(data)),
 
 //addChat(input,JSON.stringify(data))
  );
 // console.log(data);
}

  // do something with myJson


  else if (text.match(/(corona|covid|virus)/gi)) {
    // If no match, check if message contains `coronavirus`
    product = coronavirus[Math.floor(Math.random() * coronavirus.length)];
  } else {
    // If all else fails: random alternative
    product = alternative[Math.floor(Math.random() * alternative.length)];
  }

  // Update DOM
  addChat(input, product);
}

function compare(promptsArray, repliesArray, string) {
  let reply;
  let replyFound = false;
  for (let x = 0; x < promptsArray.length; x++) {
    for (let y = 0; y < promptsArray[x].length; y++) {
      if (promptsArray[x][y] === string) {
        let replies = repliesArray[x];
        reply = replies[Math.floor(Math.random() * replies.length)];
        replyFound = true;
        // Stop inner loop when input value matches prompts
        break;
      }
    }
    if (replyFound) {
      // Stop outer loop when reply is found instead of interating through the entire array
      break;
    }
  }
  return reply;
}
function addSymptom(product) {
  const messagesContainer = document.getElementById("messages");

  let userDiv = document.createElement("div");
  //userDiv.id = "user";
  //userDiv.id = "user";
  //userDiv.id = "user";
  //userDiv.id = "user";
  //userDiv.className = "user response";
  //userDiv.innerHTML = `<img src="user.png" class="avatar"><span>${input}</span>`;
  messagesContainer.appendChild(userDiv);

  let botDiv = document.createElement("div");
  let botImg = document.createElement("img");
  let botText = document.createElement("span");
  botDiv.id = "bot";
  botImg.src = "bot-mini.png";
  botImg.className = "avatar";
  botDiv.className = "bot response";
  botText.innerText = "Typing...";
  botDiv.appendChild(botText);
  botDiv.appendChild(botImg);
  messagesContainer.appendChild(botDiv);
  // Keep messages at most recent
  messagesContainer.scrollTop = messagesContainer.scrollHeight - messagesContainer.clientHeight;

  // Fake delay to seem "real"
  setTimeout(() => {
    botText.innerText = `${product}`;
    textToSpeech(product)
  }, 2000
  )

}
function addChat(input, product) {
  const messagesContainer = document.getElementById("messages");

  let userDiv = document.createElement("div");
  userDiv.id = "user";
  userDiv.className = "user response";
  userDiv.innerHTML = `<img src="user.png" class="avatar"><span>${input}</span>`;
  messagesContainer.appendChild(userDiv);

  let botDiv = document.createElement("div");
  let botImg = document.createElement("img");
  let botText = document.createElement("span");
  botDiv.id = "bot";
  botImg.src = "bot-mini.png";
  botImg.className = "avatar";
  botDiv.className = "bot response";
  botText.innerText = "Typing...";
  botDiv.appendChild(botText);
  botDiv.appendChild(botImg);
  messagesContainer.appendChild(botDiv);
  // Keep messages at most recent
  messagesContainer.scrollTop = messagesContainer.scrollHeight - messagesContainer.clientHeight;

  // Fake delay to seem "real"
  setTimeout(() => {
    botText.innerText = `${product}`;
    textToSpeech(product)
  }, 2000
  )

}