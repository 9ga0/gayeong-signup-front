import '../components/common/Common.css';
import SubmitButton from "../components/common/SubmitButton"
import '../styles/Modal.css'
import KakaoPostcodeEmbed from 'react-daum-postcode';
import react, { useState } from "react"

export default function Modal( props ) {

    //임베드 방식으로 카카오우편검색api 가져옴.
    const handleComplete = (data) => {
        let fullAddress = data.address;
        let extraAddress = '';

        if (data.addressType === 'R') {
            if (data.bname !== '') {
                extraAddress += data.bname;
            }
            if (data.buildingName !== '') {
                extraAddress += extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName;
            }
            fullAddress += extraAddress !== '' ? ` (${extraAddress})` : '';
        }

        console.log(fullAddress);
        props.setDataAddress(fullAddress);
        props.setOpenModal(false);
    };

    return (
        <>
            {!props.openModal ?
                null :
                < div className="Overlay" >
                    < main className="card-box modal-size" >
                        <KakaoPostcodeEmbed onComplete={handleComplete}
                            
                            style={{ width: "410px", height: "392px"}} />
                        <button
                            type="submit"
                            className="submit-button"
                            onClick={()=>{props.setOpenModal(false)}}
                        >
                            <p className='button-text'>닫기</p>
                        </button>
                    </main >
                </div >
            }
        </>
    )
}