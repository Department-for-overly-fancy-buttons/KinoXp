export function createHtmlElement(elementInfo){

    if(typeof elementInfo !== "object" || !("tagName" in elementInfo)){
        return null;
    }

    console.log(`tag: ${elementInfo.tagName}, classes: ${elementInfo.classes}, ids: ${elementInfo.ids}`)
    let element;

    if(typeof elementInfo.tagName === "string"){
        element = document.createElement(elementInfo.tagName);
        console.log("tag is valid string. Attempted to create element:");
        console.log(element);
    }else{
        console.log("tag is clearly an invalid string. returning element: null");
        return null;
    }

    if("classes" in elementInfo) {
        let classString = null;
        if (Array.isArray(elementInfo.classes)) {
            classString = elementInfo.classes.reduce((previousClass, currentClass) => previousClass += " " + currentClass);
            console.log(`classes has multiple classes ${classString}`);
        } else if (typeof elementInfo.classes === "string") {
            classString = elementInfo.classes;
            console.log(`classes has one string ${classString}`);
        }
        if (classString !== null) {
            element.classList.add(classString);
            console.log(`classesString is not null (${classString}) element:`);
            console.log(element);
        }
    }

    if("ids" in elementInfo) {
        let idString = null;
        if (Array.isArray(elementInfo.ids)) {
            idString = elementInfo.ids.reduce((previousId, currentId) => previousId += " " + currentId);
            console.log(`ids has multiple ids ${idString}`);
        } else if (typeof elementInfo.ids === "string") {
            idString = elementInfo.ids;
            console.log(`ids has one string ${idString}`);
        }
        if (idString !== null) {
            element.id = idString;
            console.log(`idString is not null (${idString}) element:`);
            console.log(element);
        }
    }

    if("htmlAttributes" in elementInfo){
        for(let htmlAttribute in elementInfo.htmlAttributes){
            element[htmlAttribute] = elementInfo.htmlAttributes[htmlAttribute];
        }

    }

    if("textContent" in elementInfo){
        element.textContent = elementInfo.textContent;
    }

    if(elementInfo.tagName === "a"){
        if("href" in elementInfo){
            element.href = elementInfo.href;
        }
        if("title" in elementInfo){
            element.title = elementInfo.title;
        }
    }
    console.log("returning");
    console.log(element);

    return element;

}