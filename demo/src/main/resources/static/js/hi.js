const e=React.createElement;
const str='a Fucking Fog'
const vDom=e('h1',{id:'id'},str.toUpperCase());
const vDom1=e('h2',{id:'id'},str.toLowerCase());
const vDom2=e('h3',{id:'id'},str);
const vDom3=document.querySelector('#test2');
const vDom4=e('button', {onClick: () => this.setState({liked:true})},'like');

// ReactDOM.render(e(LikeButton),vDom2);
ReactDOM.render(vDom,document.getElementById('test'));
ReactDOM.render(vDom1,document.getElementById('test2'));
ReactDOM.render(vDom2,document.getElementById('test3'));
